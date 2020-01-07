#!/bin/bash

v4l2-ctl --stream-mmap --stream-count=1 --stream-to=/photo/file-new.jpg

if [ $? -ne 0 ]; then
	echo "Failed to capture images with /dev/video0"
        exit 1
fi
sleep 10

while :
do
   mv /photo/file-new.jpg /photo/file-old.jpg
   v4l2-ctl --stream-mmap --stream-count=1 --stream-to=/photo/file-new.jpg
   if [ $? -ne 0 ]; then
	   echo "Failed to capture images with /dev/video0"
	   exit 1
   fi
   sleep 10
done
