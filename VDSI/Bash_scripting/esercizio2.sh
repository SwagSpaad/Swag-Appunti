#!/bin/bash

var="8dm7KsjU28B7v621Jls"
value="ERmFRMVZ0U2paTlJYTkxDZz09Cg"

for i in {1..40}; do
	var=$(echo $var | base64)

	#<---- If condition here:
	if [[ $var==*$value* ]]; then
		echo daje roma
	fi
done
