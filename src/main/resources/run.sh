openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem
openssl pkcs12 -export -in certificate.pem -inkey key.pem -out certificate.p12