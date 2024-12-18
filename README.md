
# Currency Calculator (Währungsrechner)
Ein Android-Projekt zur Umrechnung verschiedener Währungen.

## Projektbeschreibung

Diese Android-App ermöglicht es Benutzern, Beträge zwischen verschiedenen Währungen umzurechnen. Das Projekt wurde in zwei Versionen entwickelt:

- **Basis-Version**: Arbeitet mit vordefinierten Wechselkursen für schnelle und offline-fähige Umrechnungen
- **Erweiterte Version**: Nutzt eine API-Anbindung für Echtzeit-Wechselkurse

## Hauptfunktionen

- Währungsumrechnung zwischen verschiedenen Währungspaaren
- Wahlweise Nutzung von festen oder Echtzeit-Wechselkursen
- Benutzerfreundliche Eingabe
- Sofortige Ergebnisanzeige
- Fehlerbehandlung bei ungültigen Eingaben

## Technische Details

- Entwickelt mit Kotlin
- Benutzeroberfläche: [Compose/XML Layout]
- API-Integration: [Retrofit/Volley/etc] (in der erweiterten Version)
- Eigenes App-Icon und Design
- Ressourcenverwaltung im res-Ordner

## Testfälle

Die App wurde mit folgenden Szenarien getestet:
- Umrechnung verschiedener Währungspaare (EUR→GBP, CNY→GBP, etc.)
- Behandlung gleicher Währungen
- Überprüfung der API-Anbindung (erweiterte Version)
- Validierung von Benutzereingaben

## Design-Prinzipien

- Intuitive Benutzerführung
- Direktes Feedback bei Eingaben
- Übersichtliche Darstellung der Ergebnisse
- Konsistentes Design

---
*Entwickelt im Rahmen der Projektwoche im Fach "Mobile Apps für Google Android" im Jahr 2024 am International bib College.*
