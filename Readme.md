# Patient Management System
---
## Step 1: Getting started
```bash
# Clone the repository (first time only)
git clone https://github.com/your-org/pmis-group-project.git

# Navigate to project folder
cd pmis-group-project

# Switch to main and pull latest changes
git checkout main
git pull origin main

# Create your feature branch from main
git checkout -b feature/your-task-name

```

## Step 2: Work on Your Task
```bash

# Make changes to your code
# Add new files or modify existing ones

# Stage your changes
git add .

# Commit with meaningful message
git commit -m "[Member X] feat: add PatientDAO addPatient method"

# Push your branch to GitHub
git push origin feature/your-task-name

```

## step 3: Create a Pull Request (PR)
- Go to GitHub repository

- Click Pull Requests → New Pull Request

- Base: main ← Compare: feature/your-task-name

- Title format: `[Member X] Task Name - Short Description`

## Step 4: Code Review Process
At least 1 other member must review and approve

Reviewer checks:

- ✅ Code compiles
- ✅ Follows MVC/DAO pattern
- ✅ No merge conflicts
- ✅ Meaningful variable names
- ✅ Comments where necessary

## Step 5: Merge to Main
After approval, merge the PR to main

# Note
1. Never push in main branch - always push on your feature branch and raise a PR
2. Always pull changes from main before you push
3. Always Pull Before Creating Branch
4. Always add Pull Request (PR) Descripton