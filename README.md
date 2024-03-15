<h1 align="center">
    Resolução do desafio "PicPay" simplificado.
</h1>

## Tecnologias

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

## Descrição
Resolução do desafio do "picpay simplificado"
<br>
[Link do desafio](https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file)

### Arquitetura
![Fig-2-Architecture-flow-of-spring-boot-Applications-Spring-boot-uses-all-the-features](https://github.com/LeonardoMeireles55/Desafio-PicPay-Backend/assets/123477726/83d8b2b7-96cc-4203-a668-7b9736553a42)


### Modelagem
**Diagrama ER**
<br>
<br>
![Screenshot from 2024-03-14 22-58-34](https://github-production-user-asset-6210df.s3.amazonaws.com/123477726/313043136-f3e93c79-683e-4b9f-8688-bbe49c0de367.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240315%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240315T150538Z&X-Amz-Expires=300&X-Amz-Signature=465af97225ac80b885e500d29fa66805c1d6989faee43e2b86bcb7de181d775d&X-Amz-SignedHeaders=host&actor_id=123477726&key_id=0&repo_id=772006076)

### Transações
**Requisições**

`POST /transaction`
```json
{
	"payer" : 1,
	"payee" : 2,
	"value" : 10.50
}
```
`HTTP 200 OK`
```json
{
	"id": 9,
	"payer": 1,
	"payee": 2,
	"value": 10.50,
	"createdAt": "2024-03-14T15:16:49.555179848"
}
```
`GET /transaction`
`HTTP 200 OK`
```json
{
	"transactions": [
		{
			"id": 9,
			"payer": 1,
			"payee": 2,
			"value": 10.50,
			"createdAt": "2024-03-14T15:16:49.55518"
		},
		{
			"id": 8,
			"payer": 1,
			"payee": 2,
			"value": 10.50,
			"createdAt": "2024-03-14T13:45:51.653318"
		},
		{
			"id": 7,
			"payer": 1,
			"payee": 2,
			"value": 10.50,
			"createdAt": "2024-03-14T13:44:53.746712"
		},
		{
			"id": 6,
			"payer": 1,
			"payee": 2,
			"value": 10.50,
			"createdAt": "2024-03-14T13:43:39.720557"
		},
		{
			"id": 5,
			"payer": 1,
			"payee": 2,
			"value": 10.50,
			"createdAt": "2024-03-14T13:42:27.491491"
		}
	]
}
```
#### Requirements
* [Docker](https://www.docker.com/get-started/)
* [Docker Compose](https://docs.docker.com/compose/gettingstarted/)
* [Git](https://git-scm.com/)

#### Passo 1. Clone the repository
Clone o projeto:
```
git clone https://github.com/LeonardoMeireles55/picpay.git
```
#### Passo 2. Suba os containers
No diretório raiz do projeto, execute o comando:
```
docker-compose up or docker compose up
```
#### Passo 3. Acesse a documentação pelo link
```
http://localhost:8080/swagger-ui.html
```
