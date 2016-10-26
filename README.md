# Slow Docker Volumes

## Environment
- MacOS Sierra 10.12
- Docker for Mac 1.12.2-beta28(12906)

slow disk was also present on stable channel, I installed beta to attempt suggested fixes in bug report, which had no effect.


## Build Image
```bash

# use a make shortcut to build the image
# docker build --tag=$(TAG) dev/container

make docker-build
```



## Start docker, and build uberjar

For this example I've mapped a m2 directory that is part of the git repository, where in normal use I would reference a .m2 directory in my host home directory.

```bash

# use a make shortcut to start docker
# docker run --rm  -v $(current_path):/work -v $(m2_dir):/root/.m2 -it $(TAG) zsh

make docker-run


# you should now be inside of container

# download .m2 dependancies from maven/etc so not to include in build time
lein deps


# build uberjar 

time lein uberjar
Compiling helloapp
Created /work/target/helloapp-0.1.0-SNAPSHOT.jar
Created /work/target/helloapp-0.1.0-SNAPSHOT-standalone.jar
lein uberjar  19.17s user 17.99s system 7% cpu 8:09.32 total

```




## Same scenario on host outside of docker
```bash

time lein uberjar
Compiling helloapp
Created /Users/mike/work/docker/docker-slow-volumes/helloapp/target/helloapp-0.1.0-SNAPSHOT.jar
Created /Users/mike/work/docker/docker-slow-volumes/helloapp/target/helloapp-0.1.0-SNAPSHOT-standalone.jar

real	0m12.258s
user	0m11.652s
sys	0m3.401s

```


## Additional Info

com.docker.hyperkit is running at ~124% CPU usage during the entire 8.5 minutes. 

The SSD drive is a brand new Samsung 850 pro 512.

The computer is an older macbook pro




