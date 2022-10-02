job("gitPush conditions") {
    startOn {
        gitPush {
            // run on changes in 'main'
            branchFilter {
                +"refs/heads/main"
                // +"refs/heads/release-*"
                // -Regex("\brelease-main\b")
            }
        }
    }
    
    docker {
        beforeBuildScript {
            // Create an env variable BRANCH,
            // use env var to get full branch name,
            // leave only the branch name without the 'refs/heads/' path
            content = """
                export BRANCH=${'$'}(echo ${'$'}JB_SPACE_GIT_BRANCH | cut -d'/' -f 3)
            """
        }
        build {
            context = "docker"
            file = "./docker/Dockerfile"
        }

        push("mikedettmer.registry.jetbrains.space/p/frontend/containers/fe") {
            // Use the BRANCH and JB_SPACE_EXECUTION_NUMBER env vars
            tag = "version-0.\$JB_SPACE_EXECUTION_NUMBER-\$BRANCH"
        }
    }
}
