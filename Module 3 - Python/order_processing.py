def apply_discount(order_amount):
    if(order_amount>100):
        return order_amount- (order_amount*0.1)
    else:
        return order_amount 
        
n = apply_discount(150)
print("The final price is", n)