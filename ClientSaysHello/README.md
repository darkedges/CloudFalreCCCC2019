# Client says Hello

Client says hello, as follows:

```bash
00000c07ac01784f437dbfc70800450000f2560140004006db58ac1020c843c
7f80cd1f701bbc8b2af3449b598758018102a72a700000101080a675bce16787
abd8716030100b9010000b503035c1ea569d5f64df3d8630de8bdddd1152e75f
528ae577d2436949ce8deb7108600004400ffc02cc02bc024c023c00ac009c0
08c030c02fc028c027c014c013c012009f009e006b0067003900330016009d
009c003d003c0035002f000a00af00ae008d008c008b010000480000000b
000900000663666c2e7265000a00080006001700180019000b0002010000
0d0012001004010201050106010403020305030603000500050100000000
001200000017000052305655494338795157524c656d6443436c5246574651
675430346754456c4f52564d674e434242546b51674e513d3d
```

## Result

I am not sure if this the right way to do this, but this is my shot at it.

I saved [https://gist.githubusercontent.com/IcyApril/bb95c93a333aef24368242fe2af4c5ad/raw/677019f224379f471d158a20900029dbd3dd0dec/cf_crypto_puzzle_1_2019.txt](https://gist.githubusercontent.com/IcyApril/bb95c93a333aef24368242fe2af4c5ad/raw/677019f224379f471d158a20900029dbd3dd0dec/cf_crypto_puzzle_1_2019.txt) to a file `cloudflare`

Compiled via

```bash
javac Main.java
```

and executed via

```bash
java TOTP Main
```

it produced

```bash
♀¬☺xOC}¿ E  òV☺@ @♠ÛX¬► ÈCÇø♀Ñ÷☺»È²¯4Iµ?u?↑►*r§  ☺☺
è½ÝÑ§.uõ(®W}$6??èÞ·►?  D ÿÀ,À+À$À#À
À       À0À/À(À'À¶À‼À↕ ? ? k g 9 3 ■ ? ? = < 5 /
 ¯ ® ? ? ?☺  H   ♂        ♠cfl.re
 ↕ ►♦☺☻☺♣☺♠☺♦♥☻♥♣♥♠♥ ♣ ♣☺     ↕   ↨  R0VUIC8yQWRLemdCClRFWFQgT04gTElORVMgNCBBTkQgNQ==
```

from this I noticed `cfl.re` and `R0VUIC8yQWRLemdCClRFWFQgT04gTElORVMgNCBBTkQgNQ==`

base64 decoding ``R0VUIC8yQWRLemdCClRFWFQgT04gTElORVMgNCBBTkQgNQ==` gave me

```bash
GET /2AdKzgB
TEXT ON LINES 4 AND 5
```

so a quick curl command 

```bash
curl https://cfl.re/2AdKzgB
```

gave me

```bash
<html>
<head><title>Bitly</title></head>
<body><a href="https://www.cloudflare.com/robots.txt">moved here</a></body>
</html>
```

so an update to that command to follow redirects

```bash
curl -L https://cfl.re/2AdKzgB
```

gave me

```
#    .__________________________.                                
#    | .___________________. |==|                                
#    | | ................. | |  |                                
#    | | ::[ Dear robot ]: | |  |                                
#    | | ::::[ be nice ]:: | |  |                                
#    | | ::::::::::::::::: | |  |                                
#    | | ::::::::::::::::: | |  |                                
#    | | ::::::::::::::::: | |  |                                
#    | | ::::::::::::::::: | | ,|                                
#    | !___________________! |(c|                                
#    !_______________________!__!                                
#   /                            \                               
#  /  [][][][][][][][][][][][][]  \                              
# /  [][][][][][][][][][][][][][]  \                             
#(  [][][][][____________][][][][]  )                            
# \ ------------------------------ /                             
#  \______________________________/                              
                                                                 
                                                                 
User-agent: *                                                    
Disallow: /__esa                                                 
Disallow: /__mesa/                                               
Disallow: /__xesa/                                               
Disallow: /__csup/                                               
Disallow: /__xsla/                                               
Disallow: /__xcusp/                                              
Disallow: /__xesa/                                               
Disallow: /__xsla/                                               
Disallow: /lp                                                    
Disallow: /feedback                                              
Disallow: /langtest                                              
                                                                 
Sitemap: https://www.cloudflare.com/sitemap.xml                  
Sitemap: https://www.cloudflare.com/fr-fr/sitemap.xml            
Sitemap: https://www.cloudflare.com/de-de/sitemap.xml            
Sitemap: https://www.cloudflare.com/es-es/sitemap.xml            
Sitemap: https://www.cloudflare.com/pt-br/sitemap.xml            
                                                                 
                                                                 
#              ________                                          
#   __,_,     |        |                                         
#  [_|_/      |   OK   |                                         
#   //        |________|                                         
# _//    __  /                                                   
#(_|)   |@@|                                                     
# \ \__ \--/ __                                                  
#  \o__|----|  |   __                                            
#      \ }{ /\ )_ / _\                                           
#      /\__/\ \__O (__                                           
#     (--/\--)    \__/                                           
#     _)(  )(_                                                   
#    `---''---`                                                  
```

the text on line 4 and 5 is 

```bash
#    | | ::[ Dear robot ]: | |  |                                
#    | | ::::[ be nice ]:: | |  |
```

I could not find a way to get `wireshark` or `ssldump` to work on the data, but will keep looking at I am sure I have just cheated ;-)

## Note

I did find this handy hint to help decode Java SSSL traces via Wireshark
[https://stackoverflow.com/a/41128636](https://stackoverflow.com/a/41128636)