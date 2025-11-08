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
    }
}

rootProject.name = "MyInstagram"
include(":app")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":feature:auth")
include(":feature:feed")
include(":feature:profile")
include(":feature:post")
include(":feature:story")
include(":feature:search")
