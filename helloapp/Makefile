# Common Tasks
mkfile_path := $(abspath $(lastword $(MAKEFILE_LIST)))
current_path := $(dir $(mkfile_path))
m2_dir := $(abspath $(dir $(lastword $(MAKEFILE_LIST)))/../m2)

TAG=helloapp


# build the container
docker-build:
	docker build --tag=$(TAG) dev/container


# run the dev container
docker-run:
	docker run --rm  -v $(current_path):/work -v $(m2_dir):/root/.m2 -it $(TAG) zsh
