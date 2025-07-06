
#  Digital Signature System (Client–Server Edition)

A secure and interactive **Digital Signature System** implemented in Java, enhanced with a **Client–Server architecture** for real-world simulation of message signing and verification. This project demonstrates how digital signatures ensure **data integrity**, **authentication**, and **non-repudiation** in communication systems.

---

##  Overview

Digital signatures are a cornerstone of modern cryptography, enabling secure data exchange.
This system enables a **Client** to:

* Generate a digital signature for a message using its private key.
* Transmit the signed message to a **Server** for verification.

The **Server** then:

* Validates the signature using the public key.
* Confirms whether the message originated from the genuine client.

 **Enhancement:** Unlike the original static version, this project uses **Java sockets** for dynamic client-server communication.

---

##  Features

*  **Digital Signature Creation:** RSA-based signature generation using private keys.
*  **Signature Verification:** Validate signatures using public keys on the server-side.
*  **Client-Server Communication:** Simulated over TCP sockets.
*  **Key Pair Management:** Store and retrieve RSA key pairs from local files.
*  **Error Handling:** Robust validation and error messages for invalid signatures.

---

##  Architecture

```
+-----------------+             +-----------------+
|     Client      |             |     Server      |
|-----------------|             |-----------------|
| Generate RSA    |             | Load Public Key |
| Sign Message    |             | Receive Message |
| Send Data       | --------->  | Verify Signature|
+-----------------+             +-----------------+
```

---


##  How to Run

###  Step 1: Generate RSA Key Pair

1. Use `RSAKeyManager` utility to generate private and public keys.
2. Store them in `keys/private_key.txt` and `keys/public_key.txt`.

---

###  Step 2: Start the Server

```bash
cd server
javac Server.java
java Server
```

---

###  Step 3: Start the Client (in a new terminal)

```bash
cd client
javac Client.java
java Client
```

---

###  Workflow

1. Client signs a message using its **private key**.
2. The signed message is sent to the Server.
3. Server verifies the signature using the **public key**.
4. Verification result (Valid/Invalid) is displayed.

---

##  Technologies Used

*  **Java** (JDK 8+)
*  **RSA Cryptography** (Java Security API)
*  **Socket Programming (TCP)**
*  **File I/O**

---



