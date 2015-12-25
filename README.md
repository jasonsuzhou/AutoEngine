# AutoEngine
AUTO generate CURD java web application

---
###Inital a GitHub project
- Create project via eclipse
- Create repository via github with the same project name 
- Use GitBash cd into the project root path
```bash
git init
git add . # . means add all the files, not committed yet
git commit -m "Initial the project" # just commit the code to local repository
```
- Commit to remote github
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
###Connect GitHub without username/password (For Windows 7 System)
- Use GitBash to generate public/private keys
```bash
ssh-keygen -C "jason.yao525@gmail.com" -t rsa #enter file name: id_rsa
```
- Keys will generate to the folder C:/Users/%username%/
- Copy the keys to the folder C:/Users/%username%/.ssh/, windows need command to create the folder which has dot(.) prefix
```bash
cd C:/Users/%username%/
mkdir .ssh
```
- Open the id_rsa.pub file and copy the content to GitHub SSH Keys settings
```bash
notepad id_rsa.pub
```

