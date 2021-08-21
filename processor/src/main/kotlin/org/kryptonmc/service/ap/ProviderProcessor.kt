package org.kryptonmc.service.ap

import com.google.gson.GsonBuilder
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Messager
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror
import javax.tools.Diagnostic
import javax.tools.StandardLocation
import org.kryptonmc.service.Provider
import org.kryptonmc.service.ServiceProvider
import org.kryptonmc.service.loader.ProviderMethod
import java.io.BufferedWriter
import java.io.IOException

@SupportedAnnotationTypes("org.kryptonmc.service.ServiceProvider")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
class ProviderProcessor : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (roundEnv.processingOver()) return false
        val providers = mutableMapOf<String, SerializedProvider>()
        roundEnv.getElementsAnnotatedWith(ServiceProvider::class.java).forEach { element ->
            if (element.kind != ElementKind.CLASS) {
                processingEnv.messager.error("Only classes can be annotated with ${ServiceProvider::class.java.canonicalName}!")
                return false
            }

            element as TypeElement
            val meta = element.getAnnotation(ServiceProvider::class.java)
            if (!ID_REGEX.matches(meta.id)) {
                processingEnv.messager.error("Invalid ID for service provider ${element.qualifiedName}! IDs must start alphabetically, and only contain alphanumeric characters, dashes or underscores! (Regex: $ID_REGEX)")
                return false
            }
            if (providers.containsKey("${meta.id}:${meta.version}")) {
                processingEnv.messager.error("Duplicate ID and version found! ID: ${meta.id}, version: ${meta.version}!")
                return false
            }

            val methods = mutableListOf<ProviderMethod>()
            element.enclosedElements.asSequence()
                .filter { it.kind == ElementKind.METHOD }
                .filter { it.getAnnotation(Provider::class.java) != null }
                .map { it as ExecutableElement }
                .forEach { method ->
                    if (method.returnType.kind == TypeKind.VOID || method.returnType.kind == TypeKind.NULL) {
                        processingEnv.messager.error("Provider methods cannot return void or null!")
                        return false
                    }
                    val componentName = method.getAnnotation(Provider::class.java).name
                    val parameters = method.parameters.map { it.asType().toString() }.toTypedArray()
                    methods.add(SerializedMethod(method.simpleName.toString(), parameters, method.returnType.toString(), componentName))
                }
            methods.sortWith(Comparator { o1, o2 ->
                if (o1.parameters.contains(o2.returnType)) return@Comparator 1
                if (o2.parameters.contains(o1.returnType)) return@Comparator -1
                0
            })
            val provider = SerializedProvider(
                meta.id,
                meta.version,
                element.qualifiedName.toString(),
                methods
            )
            providers["${meta.id}:${meta.version}"] = provider
        }
        try {
            val fileObject = processingEnv.filer.createResource(StandardLocation.CLASS_OUTPUT, "", "service-providers.json")
            BufferedWriter(fileObject.openWriter()).use { GsonBuilder().setPrettyPrinting().create().toJson(providers.values, it) }
        } catch (exception: IOException) {
            processingEnv.messager.error("Unable to generate service provider metadata file!")
        }
        return false
    }
}

private val ID_REGEX = "[a-z][a-z0-9-_]{0,127}".toRegex()

private fun Messager.warn(message: String) = printMessage(Diagnostic.Kind.WARNING, message)

private fun Messager.error(message: String) = printMessage(Diagnostic.Kind.ERROR, message)
