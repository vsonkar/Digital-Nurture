import threading
import time
import json
import os

class Inventory:
    def __init__(self):
        self.items = {}

    def add_item(self, item_name, quantity):
        if item_name in self.items:
            self.items[item_name] += quantity
        else:
            self.items[item_name] = quantity

    def remove_item(self, item_name, quantity):
        if item_name in self.items:
            self.items[item_name] -= quantity
            if self.items[item_name] <= 0:
                del self.items[item_name]
        else:
            print(f"Item {item_name} not found in inventory.")

    def check_stock(self, item_name):
        return self.items.get(item_name, 0)

    def save_to_file(self, filename):
        try:
            with open(filename, 'w') as file:
                json.dump(self.items, file)
            print(f"Inventory saved to {filename}")
        except IOError as e:
            print(f"Error saving to file: {e}")

    def load_from_file(self, filename):
        try:
            if os.path.exists(filename):
                with open(filename, 'r') as file:
                    self.items = json.load(file)
                print(f"Inventory loaded from {filename}")
            else:
                print(f"File {filename} does not exist.")
        except (IOError, json.JSONDecodeError) as e:
            print(f"Error loading from file: {e}")

    def check_restocking(self, threshold):
        low_stock_items = {item: qty for item, qty in self.items.items() if qty < threshold}
        if low_stock_items:
            print("Restocking alert for the following items:")
            for item, qty in low_stock_items.items():
                print(f"{item}: {qty} left")

def restocking_alerts(inventory, threshold, interval):
    while True:
        inventory.check_restocking(threshold)
        time.sleep(interval)

inventory = Inventory()
inventory.add_item("Apples", 10)
inventory.add_item("Bananas", 5)
inventory.add_item("Oranges", 15)
inventory.remove_item("Bananas", 3)

inventory.save_to_file("inventory.json")

inventory.load_from_file("inventory.json")

print("Loaded Inventory:")
for item, qty in inventory.items.items():
    print(f"{item}: {qty}")

threshold = 5
interval = 10  
thread = threading.Thread(target=restocking_alerts, args=(inventory, threshold, interval), daemon=True)
thread.start()

try:
    while True:
        time.sleep(1)
except KeyboardInterrupt:
    print("Program terminated.")
