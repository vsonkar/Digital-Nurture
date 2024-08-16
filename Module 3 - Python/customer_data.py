def update_purchase(customer_data, name, amount):
    customer_data[name] = amount

customer_data = {'Alice': 120, 'Bob': 75, 'Charlie': 90}
update_purchase(customer_data,'Bob', 45)
print(customer_data)