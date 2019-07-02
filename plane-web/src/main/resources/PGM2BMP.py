# 该python脚本用于将PGM转换成BMP文件
import os
from PIL import Image
from sys import argv

originPath = argv[1]
targetDir = argv[2]

path= os.path.splitext(originPath)
if path[1] == '.pgm':
    filename = os.path.basename(originPath).split('.')[0]
    im = Image.open(originPath)
    im.save(targetDir+filename+'.bmp', "BMP")
    print('success')
else:
    print('fail')
