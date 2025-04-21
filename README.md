# 🏔️ Klub Wysokogórski – Aplikacja Webowa

Projekt aplikacji webowej dla Klubu Wysokogórskiego, stworzony w ramach przedmiotu BDBT.  
Celem aplikacji jest umożliwienie zarządzania członkami klubu, wyprawami górskimi oraz innymi powiązanymi zasobami.

## 🚀 Funkcjonalności

- Rejestracja i logowanie użytkowników
- Zarządzanie profilami członków klubu
- Tworzenie i edycja wypraw górskich
- Przypisywanie członków do wypraw
- Panel administratora do zarządzania użytkownikami i wyprawami

## 🛠️ Technologie

- **Backend:** Java 17, Spring Boot
- **Frontend:** HTML, CSS, JavaScript
- **Baza danych:** Oracle Database
- **Inne:** Maven, JDBC, ojdbc11

## ⚙️ Uruchomienie projektu

1. **Klonowanie repozytorium:**
   ```bash
   git clone https://github.com/Pioter54/BDBT.git
2. **Baza danych:**
    - Zainstaluj i uruchom baze danych 
    - Stwórz strukture bazy danych za pomocą *BDBT_SQL.SQL*
    - Zaktualizuj plik *application.properties* w katalogu *src/main/resources* z odpowiednimi danymi dostępowymi
3. **Budowanie i uruchamianie aplikacji:**
    ```bash
    mvn clean install
    mvn spring-boot:run

## 📁 Struktura projektu
```graphql
BDBT/
├── src/
│   ├── main/
│   │   ├── java/           # Kod źródłowy aplikacji
│   │   └── resources/
│   │       ├── templates/  # Szablony Thymeleaf
│   │       └── static/     # Pliki statyczne (CSS, JS)
├── BDBT_SQL.SQL            # Skrypt SQL do utworzenia struktury bazy danych
├── pom.xml                 # Plik konfiguracyjny Maven
└── README.md               # Niniejszy plik
```