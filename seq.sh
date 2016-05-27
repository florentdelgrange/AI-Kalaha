#!/bin/sh
if [ "$1" = "explain" -o "$1" = "generate" ]; then
	echo digraph \{
	python seq.py $@ |sort|uniq
	echo \}
else
	python seq.py $@
fi
