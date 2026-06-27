
# Campus-Jek — Integrated Campus Service Platform

A console-based Java CLI application simulating a campus integrated service platform. Students can order transportation, food, and document printing services through an interactive menu. Orders are tracked in a daily history, saved to JSON, and printed as a final receipt.

---

## What It Does

Campus-Jek acts as a lightweight on-campus service aggregator. When a student launches the program, they enter their name and student ID. The system then checks whether that student ID has been used before — if so, it loads their previous order history automatically. From there, the student can place orders across three service categories: transportation (ride to a destination), food delivery, and document printing. Each service has its own fare calculation logic. At any point, the student can view their order history filtered by category or all at once. When done, the program prints a full receipt summarizing every order and the total amount due for that session.

---

## How It Works

1. **Student identification** — the program reads the student's name and ID from the keyboard. If a JSON file matching that ID already exists in the `data/` folder, the previous session's history is loaded into memory.
2. **Service selection** — the main menu presents three service options. Each option prompts the student for relevant details (destination, food item, number of pages, etc.) and calculates the fare automatically.
3. **Order tracking** — every completed order is stored as a `Layanan` object inside `RiwayatHarian`, which holds a `List<Layanan>`. This keeps all orders for the current session in memory.
4. **Persistent storage** — when the student exits or requests a receipt, the full profile and order list are serialized and written to a JSON file in `data/`. This means data survives between sessions.
5. **Receipt printing** — the program prints a formatted summary of all orders placed during the session, along with the grand total, before exiting.

---

## Features

- Input student data (name, student ID) from the keyboard.
- Choose from transportation, food, and document printing services via an interactive menu.
- View order history through a submenu — all orders or filtered by service category.
- Automatic fare calculation based on the selected service type.
- Store all orders in a daily history using `List<Layanan>`.
- Save student profile and order history to a JSON file in the `data/` folder.
- Automatically reload previous history when the same student ID is entered again.
- Print a final payment receipt showing all orders and the total bill for the day.

---

## What Has Been Implemented

### OOP Concepts
- **Inheritance** — `Transportasi`, `Makanan`, and `CetakTugas` all extend a shared `Layanan` base class, inheriting common attributes like service name and fare.
- **Encapsulation** — all class fields are private and accessed through getters and setters, keeping internal data protected from direct modification.
- **Polymorphism** — the `RiwayatHarian` list stores all orders as `Layanan` objects, allowing different service types to be handled through a single unified interface.
- **Abstraction** — `Layanan` defines the contract (e.g. `hitungTarif()`) that every service subclass must implement in its own way.

### Data & Storage
- **`Mahasiswa`** — stores the student's name, ID, and their `RiwayatHarian` for the session.
- **`RiwayatHarian`** — holds a `List<Layanan>` that accumulates all orders placed during a session.
- **JSON persistence** — student profiles and order histories are written to and read from JSON files in the `data/` folder using a `DataManager` class.
- **Session continuity** — when a student ID is entered, the program checks for an existing JSON file and restores the previous session's data automatically.

### Service Logic
- **Transportasi** — calculates fare based on distance to the destination.
- **Makanan** — calculates fare based on the food items ordered.
- **CetakTugas** — calculates fare based on the number of pages to be printed.

### CLI & UX
- **Main menu** — presents all available services and navigates to submenus.
- **History submenu** — lets students view all orders or filter by service category.
- **Receipt** — prints a formatted end-of-session summary with every order and the grand total.

---

## Advantages

- **Persistent data** — order history is saved to JSON and reloaded automatically, so no data is lost between program runs.
- **OOP design** — the codebase uses inheritance, encapsulation, and polymorphism. All service types extend a shared `Layanan` base class, making it easy to add new service categories without rewriting existing logic.
- **Automatic fare calculation** — each service type handles its own pricing logic internally, so the main menu never needs to know how fares are computed.
- **Filtered history view** — students can review orders by category, making it easy to track spending per service type.
- **Scalable structure** — adding a new service only requires creating a new subclass of `Layanan` and registering it in the menu, with no changes needed to the history or storage system.

---

## How to Run

Compile:

```bash
javac -d out src/*.java
```

Run:

```bash
java -cp out Main
```

Once the program starts, enter your student data and select a service from the menu.
