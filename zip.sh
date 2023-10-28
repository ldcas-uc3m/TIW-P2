#!/bin/bash
# Bash script to zip the whole project in order to make it deriverable
# please make sure zip, pv and texlive are installed

OUTFILE=GRUPO81.02.Practica2.zip


# compile the report (and save it to root folder)
echo "Compiling the report..."

latexmk -cd -shell-escape -pdf report/report.tex 

cp report/report.pdf .


# zip it
echo "Zipping..."
zip -r $OUTFILE *.war report.pdf

# cleanup
echo "Cleaning up..."
rm report.pdf
