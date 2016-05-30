#!/bin/bash

#Parámetros:
#-e      Genera el jar del Engine
#-s      Genera el jar del Server
#-c      Genera el jar del Client
#-g      Genera el jar del Game pasado por parámetro

if [[ ! -d build ]]; then
	echo -e "It doesn't exist the directory build. Please compile the project first!"
	exit 0
fi

cd build/classes/main

ARGS=$(getopt -o hescg: -l "help,engine,server,client,game:" -- "$@")
eval set -- "$ARGS"



ROOT="ar/fiuba/tdd/tp"

CLIENT="$ROOT/client"
CONNECTION="$ROOT/connection"
CONSOLE="$ROOT/console"
DRIVER="$ROOT/driver"
ENGINE="$ROOT/engine"
EXCEPTIONS="$ROOT/exceptions"
ICOMMAND="$ROOT/icommand"
INTERPRETER="$ROOT/interpreter"
MODEL="$ROOT/model"
SERVER="$ROOT/server"

CONSTANTS="$ROOT/Constants.class"

ROOT_JAVA="ar.fiuba.tdd.tp"
MANIFEST="manifest.mf"



while true; do
	command="jar "
	case "$1" in 
		-h|--help)
			echo "
-h, --help	Muestra esta ayuda y finaliza
-e, --engine	Genera el jar del Engine
-s, --server	Genera el jar del Server
-c, --client	Genera el jar del Client
-g, --game	Genera el jar del Game pasado por parámetro
";
			exit 0;
			;;
		-e|--engine)
			manifest="Manifest-version: 1.0\n"
			echo -e "$manifest" > "$ENGINE/$MANIFEST"
			command+="cfm $ENGINE/Engine.jar $ENGINE/$MANIFEST $ENGINE/*.class $ICOMMAND/*.class $INTERPRETER/*.class $MODEL/Game.class $MODEL/GameBuilder.class"
			eval "$command"
			;;
		-s|--server)
			manifest="Manifest-version: 1.0\nMain-Class: $ROOT_JAVA.server.MainServer\nClass-Path: ../engine/Engine.jar\n"
			echo -e "$manifest" > "$SERVER/$MANIFEST"
			command+="cfm $SERVER/Server.jar $SERVER/$MANIFEST $SERVER/*.class $CONNECTION/*.class $EXCEPTIONS/*.class $CONSOLE/*.class $CONSTANTS $DRIVER/*.class"
			eval "$command"
			;;
		-c|--client)
			manifest="Manifest-version: 1.0\nMain-Class: $ROOT_JAVA.client.MainClient"
			echo -e "$manifest" > "$CLIENT/$MANIFEST"
			command+="cfm $CLIENT/Client.jar $CLIENT/$MANIFEST $CLIENT/*.class $CONNECTION/*.class $EXCEPTIONS/*.class $CONSOLE/*.class $CONSTANTS"
			eval "$command"
			;;
		-g|--game)
			game="$2"
			command+="cvf $MODEL/$game""Configuration.jar $MODEL/$game""Configuration.class"
			eval "$command"
			shift
			;;
		--)
			break;
			;;
	esac
	shift	# avanza en la lista de parametros
done	 

cd ../../..	# sale del lugar donde genera los jars