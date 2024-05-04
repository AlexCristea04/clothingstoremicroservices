#!/usr/bin/env bash

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=customers-service \
--package-name=com.example.customers \
--groupId=com.example.customers \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
customers-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=employees-service \
--package-name=com.example.employees \
--groupId=com.example.employees \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
employees-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=products-service \
--package-name=com.example.products \
--groupId=com.example.products \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
products-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=orders-service \
--package-name=com.example.orders \
--groupId=com.example.orders \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
orders-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=api-gateway \
--package-name=com.example.apigateway \
--groupId=com.example.apigateway \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
api-gateway

