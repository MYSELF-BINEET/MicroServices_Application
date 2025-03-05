# Microservice_Application

## Overview
This repository follows a multi-branch development strategy. It is a microservices-based application built using Spring Boot, API Gateway, Service Registry, RabbitMQ, Docker, and deployed on AWS Kubernetes (K8s). Each branch represents a different service in the project.

## Branch Structure

- `main` - The stable production-ready branch.
- `develop` - The active development branch where new features are merged before going to `main`.
- `company` - Microservice for managing company-related data.
- `jobs` - Microservice for handling job-related functionalities.
- `reviews` - Microservice for managing job reviews.
- `service-reg` - Service registry for microservice discovery.
- `gateway` - API Gateway for routing requests.
- `config_server` - Configuration server for managing centralized settings.

## Getting Started

### Cloning the Repository
```bash
git clone https://github.com/your-username/Microservice_Application.git
cd Microservice_Application
```

### Switching Between Branches
```bash
git checkout <branch-name>
```

### Creating a New Feature Branch
```bash
git checkout -b feature/<feature-name> develop
git push origin feature/<feature-name>
```

### Merging Changes
#### Merging a Feature Branch into Develop
```bash
git checkout develop
git merge feature/<feature-name>
git push origin develop
```

#### Merging Develop into Main (Release)
```bash
git checkout main
git merge develop
git push origin main
git tag -a vX.X.X -m "Release version X.X.X"
git push origin vX.X.X
```

### Hotfix Workflow
```bash
git checkout -b hotfix/<hotfix-name> main
git commit -am "Fix: description of fix"
git checkout main
git merge hotfix/<hotfix-name>
git checkout develop
git merge hotfix/<hotfix-name>
git push origin main develop
```

## Deployment on AWS Kubernetes

### Prerequisites
- AWS account with EKS configured
- Kubernetes CLI (kubectl) installed
- Docker installed
- Helm installed (for managing Kubernetes deployments)

### Building and Pushing Docker Images
```bash
docker build -t your-dockerhub-username/microservice-application:latest .
docker push your-dockerhub-username/microservice-application:latest
```

### Deploying to Kubernetes
```bash
kubectl apply -f k8s/
```

## Contributing
1. Fork the repository
2. Create a new branch (`feature/<feature-name>`)
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For any issues, reach out to [your-email@example.com](mailto:your-email@example.com).

