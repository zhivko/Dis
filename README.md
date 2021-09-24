"# Dis" 


##testing in gitlab
There is Dockerfile in root of project that is used to test && build on gitlab:
```
docker login gitlab.telekom.si:4567 && docker build -t gitlab.telekom.si:4567/erenderwebui/dis . && docker push gitlab.telekom.si:4567/erenderwebui/dis
```

