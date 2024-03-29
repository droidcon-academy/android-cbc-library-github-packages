pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        val propertiesFile = File("local.properties")
        val githubProperties = java.util.Properties()
        githubProperties.load(java.io.FileInputStream(propertiesFile))

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/droidcon-academy/android-cbc-library-github-packages")
            credentials {
                username = githubProperties["user"] as String?
                password = githubProperties["key"] as String?
            }
        }
    }
}

rootProject.name = "GithubPackage"
include(":app")
include(":StringExtension")
