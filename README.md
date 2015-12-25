# AutoEngine
AUTO generate CURD java web application

---
1. create project via eclipse
2. create repository via github with the same project name 
3. Use GitBash cd into the project root path
```bash
git init
git add . # . means add all the files, not committed yet
git commit -m "Initial the project" # just commit the code to local repository
```
4. Commit to remote github
```bash
git remote add origin https://github.com/jasonsuzhou/AutoEngine.git
git pull https://github.com/jasonsuzhou/AutoEngine.git master
git push -u origin master
```
###Commit change to remote github
```bash
#modified README.md and commit to github
git add README.md #add to cache
git commit -m "Modify README.md file" #commit to local repository
git push -u origin master #commit to the remote github repository
```

