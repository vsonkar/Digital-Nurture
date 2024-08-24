# Lists
product_names = ["Apples", "Bananas", "Oranges"]

def add_product(product_list, product_name):
    product_list.append(product_name)
    print(f"{product_name} added.")

def remove_product(product_list, product_name):
    if product_name in product_list:
        product_list.remove(product_name)
        print(f"{product_name} removed.")
    else:
        print(f"{product_name} not found.")

def update_product(product_list, old_name, new_name):
    if old_name in product_list:
        index = product_list.index(old_name)
        product_list[index] = new_name
        print(f"{old_name} updated to {new_name}.")
    else:
        print(f"{old_name} not found.")

# Dictionaries
product_details = {
    "Apples": {"quantity": 50, "price": 0.5},
    "Bananas": {"quantity": 30, "price": 0.3},
    "Oranges": {"quantity": 20, "price": 0.7}
}

def add_product_details(product_dict, name, quantity, price):
    product_dict[name] = {"quantity": quantity, "price": price}
    print(f"Product {name} added with quantity {quantity} and price {price}.")

def update_product_details(product_dict, name, quantity=None, price=None):
    if name in product_dict:
        if quantity is not None:
            product_dict[name]["quantity"] = quantity
        if price is not None:
            product_dict[name]["price"] = price
        print(f"Product {name} updated with quantity {quantity} and price {price}.")
    else:
        print(f"Product {name} not found.")

def remove_product_details(product_dict, name):
    if name in product_dict:
        del product_dict[name]
        print(f"Product {name} removed.")
    else:
        print(f"Product {name} not found.")

# Tuples
product_catalog = (
    ("Apples", 50, 0.5),
    ("Bananas", 30, 0.3),
    ("Oranges", 20, 0.7)
)

def display_catalog(catalog):
    for product in catalog:
        print(f"Product: {product[0]}, Quantity: {product[1]}, Price: {product[2]}")

# Sets
product_categories = {"Fruit", "Vegetable"}

def add_category(category_set, category):
    category_set.add(category)
    print(f"Category {category} added.")

def remove_category(category_set, category):
    if category in category_set:
        category_set.remove(category)
        print(f"Category {category} removed.")
    else:
        print(f"Category {category} not found.")

# Combining Collections
def generate_report(product_dict):
    sorted_products = sorted(product_dict.items(), key=lambda item: item[1]["price"])
    print("Product Report (sorted by price):")
    for name, details in sorted_products:
        print(f"{name}: ${details['price']}, Quantity: {details['quantity']}")

def find_products_in_price_range(product_dict, min_price, max_price):
    products_in_range = {name for name, details in product_dict.items() if min_price <= details["price"] <= max_price}
    return products_in_range

if __name__ == "__main__":
    # List operations
    add_product(product_names, "Grapes")
    update_product(product_names, "Bananas", "Blueberries")
    remove_product(product_names, "Oranges")

    # Dictionary operations
    add_product_details(product_details, "Grapes", 25, 1.2)
    update_product_details(product_details, "Apples", price=0.6)
    remove_product_details(product_details, "Bananas")

    # Display catalog
    display_catalog(product_catalog)

    # Set operations
    add_category(product_categories, "Dairy")
    remove_category(product_categories, "Vegetable")

    # Combining collections
    generate_report(product_details)
    products_in_range = find_products_in_price_range(product_details, 0.4, 1.0)
    print(f"Products in price range: {products_in_range}")
