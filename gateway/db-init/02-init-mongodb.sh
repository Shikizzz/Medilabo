#!/bin/bash

echo "########### Loading data to Mongo DB ###########"
mongoimport --jsonArray --db medilabo --collection notes --file /tmp/data/medilabo.notes.json