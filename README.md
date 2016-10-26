# Slow Docker Volumes

## Environment
- MacOS Sierra 10.12
- Docker for Mac 1.12.2-beta28(12906)

slow disk was also present on stable channel, I installed beta to attempt suggested fixes in bug report.


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
Warning: specified :main without including it in :aot.
Implicit AOT of :main will be removed in Leiningen 3.0.0.
If you only need AOT for your uberjar, consider adding :aot :all into your
:uberjar profile instead.
Compiling helloapp
Created /work/target/helloapp-0.1.0-SNAPSHOT.jar
Created /work/target/helloapp-0.1.0-SNAPSHOT-standalone.jar
lein uberjar  7.73s user 9.94s system 12% cpu 2:26.34 total

```




## Same scenario on native host
```bash

time lein uberjar

Compiling helloapp
Created /Users/mike/work/docker/docker-slow-volumes/helloapp/target/helloapp-0.1.0-SNAPSHOT.jar
Created /Users/mike/work/docker/docker-slow-volumes/helloapp/target/helloapp-0.1.0-SNAPSHOT-standalone.jar

real	0m6.393s
user	0m7.532s
sys	0m1.376s

```


## Analysis

A build of an uberjar on the host for a simple hello world build takes ~7.5 seconds. 

Uberjar builds inside the docker container take ~2.5 minutes to build. 

Builds inside a container are ~20 times slower, and the CPU is pegged at 100% the entire time.

When building normal applications that include dependancies, specifically in my case the AWS lambda toolkit, are included I am seeing build times of ~8.5 minutes at 100% CPU usage during the entire build.






