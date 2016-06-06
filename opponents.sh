#!/bin/sh
#extracts opponents from a tournament log
sed -n "/vs/s/ vs. /\n/p"
