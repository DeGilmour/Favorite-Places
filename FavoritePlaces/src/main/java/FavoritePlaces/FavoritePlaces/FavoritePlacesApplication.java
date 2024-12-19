package FavoritePlaces.FavoritePlaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FavoritePlacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FavoritePlacesApplication.class, args);
    }
}


// O Desafio
//  O tema do desafio é livre, use a criatividade e desenvolva uma aplicação que atenda os critérios abaixo:
// Faça um desenho de solução para explicação da sua aplicação;
//d Sua aplicação deverá prover a capacidade de realizar as operações de busca de cep em uma api externa (de preferência para fazer a api mocada com Wiremock, Mockoon ou similar);
// Os logs das consultas precisam ser gravados em base de dados, com o horário da consulta e os dados que retornaram da api.
// Sua aplicação deverá utilizar os conceitos básicos de SOLID;
// Seu repositório deverá ser exposto publicamente no Git. 
// Tecnologias obrigatórias:
// •Java versão 11 ou superior
// •Banco de dados relacional ou não relacional
// Será um diferencial:
// - Utilização de Docker para criação do banco de dados e mocks
// - AWS