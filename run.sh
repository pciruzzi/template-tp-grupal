#!/bin/bash

#Parámetros:
#-s      Ejecuta el Server
#-c      Ejecuta el Client

if [[ ! -d build ]]; then
	echo -e "It doesn't exist the directory build. Please compile the project first!"
	exit 0
fi

cd build/classes/main

ARGS=$(getopt -o hsc -l "help,server,client" -- "$@")
eval set -- "$ARGS"

ROOT="ar/fiuba/tdd/tp"
SERVER="$ROOT/server/Server.jar"
CLIENT="$ROOT/client/Client.jar"

while true; do
	command="java -jar "
	case "$1" in 
		-h|--help)
			echo "
-h, --help	Muestra esta ayuda y finaliza
-s, --server	Ejecuta el Server
-c, --client	Ejecuta el Client
";
			exit 0;
			;;
		-s|--server)
			command+="$SERVER"
			eval "$command"
			#gnome-terminal -e "$command"
			#gnome-terminal -x sh -c "$command; bash"
			;;
		-c|--client)
			command+="$CLIENT"
			eval "$command"
			#gnome-terminal -e "$command" #Para ejecutar en otra terminal, pero que se cierra cuando termina
			#gnome-terminal -x sh -c "$command; bash" #Para ejecutar en otra terminal, que queda abierta
			;;
		--)
			break;
			;;
	esac
	shift	# avanza en la lista de parametros
done

cd ../../..	# sale del lugar donde ejecuto