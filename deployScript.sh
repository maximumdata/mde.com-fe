docker login mikedettmer.registry.jetbrains.space -u mike
docker pull mikedettmer.registry.jetbrains.space/p/frontend/containers/fe:version-0.1-main
docker stop mikedettmer.registry.jetbrains.space/p/frontend/containers/fe:version-0.1-main
docker run -d -p 80:80 mikedettmer.registry.jetbrains.space/p/frontend/containers/fe:version-0.1-main
