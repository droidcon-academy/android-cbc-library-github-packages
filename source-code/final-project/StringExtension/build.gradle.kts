import groovy.util.Node
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("maven-publish")
}

android {
    namespace = "dev.sunnat629.stringextension"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        version = "0.0.2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

afterEvaluate {
    publishing {
        val implementationDependencies = configurations["implementation"].allDependencies.toList()
        val kaptDependencies = configurations["kapt"].allDependencies.toList()
        val testImplementationDependencies =
            configurations["testImplementation"].allDependencies.toList()
        val androidTestImplementationDependencies =
            configurations["androidTestImplementation"].allDependencies.toList()

        val addDependenciesToPom: (List<Dependency>, Node) -> Unit =
            { dependencies, dependenciesNode ->
                dependencies.forEach {
                    val dependencyNode = dependenciesNode.appendNode("dependency")
                    dependencyNode.appendNode("groupId", it.group)
                    dependencyNode.appendNode("artifactId", it.name)
                    dependencyNode.appendNode("version", it.version)
                }
            }

        publications {
            create<MavenPublication>("release") {
                logger.lifecycle("Get version. version = $version")

                groupId = "droidcon-academy"
                artifactId = "stringextensions"
                version = version
                artifact("build/outputs/aar/${project.name}-release.aar")

                pom.withXml {
                    val dependenciesNode = asNode().appendNode("dependencies")
                    listOf(
                        implementationDependencies,
                        kaptDependencies,
                        testImplementationDependencies,
                        androidTestImplementationDependencies
                    ).forEach { dependencies ->
                        addDependenciesToPom(dependencies, dependenciesNode)
                    }
                }
            }

            // Repository configuration
            val githubProperty = Properties().apply {
                load(FileInputStream(rootProject.file("local.properties")))
            }

            repositories {
                maven {
                    name = "GitHubPackages"
                    url =
                        uri("https://maven.pkg.github.com/droidcon-academy/android-cbc-library-github-packages")
                    credentials {
                        username = githubProperty["user"] as? String
                        password = githubProperty["key"] as? String
                    }
                }
                maven {
                    name = "LocalGitHubPackages"
                    url = uri("file://${layout.buildDirectory.get().asFile}/publications")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}