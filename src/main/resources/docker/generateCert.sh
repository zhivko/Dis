openssl req -new -newkey rsa:2048 -nodes -keyout mydomain.key -out mydomain.csr \
   -addext "subjectAltName=DNS:klemensComp.ts.telekom.si,IP:10.115.4.149" \
   -addext "extendedKeyUsage=serverAuth,clientAuth" \
   -addext "keyUsage=nonRepudiation,digitalSignature,keyEncipherment,keyAgreement" \
   -subj "/CN=klemen-HP-EliteBook-850-G7-Notebook-PC.ts.telekom.si" \
   -days 10000
