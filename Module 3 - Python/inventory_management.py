def check_inventory(inventory):
    for item in inventory:
        if item[1]==0:
            print(f"{item[0]} is out of stock.")
        else:
            print(f"{item[0]} is in stock.")

inventory = [('Biscuit', 10), ('Cheese', 0), ('Oats', 5)]
check_inventory(inventory)