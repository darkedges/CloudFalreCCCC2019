# RPKI

At Cloudflare, we just setup [RPKI](https://blog.cloudflare.com/rpki-details/): we signed a few hundred prefixes in order to reduce route leaks. But some of the prefixes hide a secret message. Find the ROAs that look different, decode the word!

## Result

First download

```bash
curl ripe-rpki.json -o http://localcert.ripe.net:8088/export.json
```

```bash
javac *.java -cp gson-2.8.5.jar;ipaddress-5.0.0.jar
```

```bash
java -cp gson-2.8.5.jar;ipaddress-5.0.0.jar;. App
```

produces

```bash
                                            Prefix                 Asn                                                Ta MaxLength
          2803:f800:cfcf:cfcf:cfcf:cfcf:cfcf:3/128        AS4200000097                                  LACNIC RPKI Root       128
          2803:f800:cfcf:cfcf:cfcf:cfcf:cfcf:5/128        AS4200000111                                  LACNIC RPKI Root       128
          2803:f800:cfcf:cfcf:cfcf:cfcf:cfcf:1/128        AS4200000098                                  LACNIC RPKI Root       128
          2803:f800:cfcf:cfcf:cfcf:cfcf:cfcf:4/128        AS4200000118                                  LACNIC RPKI Root       128
          2803:f800:cfcf:cfcf:cfcf:cfcf:cfcf:2/128        AS4200000114                                  LACNIC RPKI Root       128

```

Subtract `AS4200000` from each ASN and we get

| value | INT | ASCII |
| ----- | --- | ----- |
| AS4200000097 |  97 | a |
| AS4200000111 | 111 | o |
| AS4200000098 |  98 | b | 
| AS4200000118 | 118 | v |
| AS4200000114 | 114 | r |


RIPE : http://localcert.ripe.net:8088/export.json
CF rpki:     https://rpki.cloudflare.com/rpki.json
ip range: https://www.cloudflare.com/ips/