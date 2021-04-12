#!/bin/bash

post() {
  echo "Sending $1"
  curl -X POST -H "Content-Type: application/json" -d "$1" http://localhost:8301/
}

post '{"id":"id-1","name":"name-1","count":1,"amount":11.1}'
post '{"id":"id-2","name":"name-2","count":2,"amount":22.2}'
post '{"id":"id-3","name":"name-3","count":3,"amount":33.3,"error":true}'