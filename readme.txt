Printer.jar can be ran from the command line with 
"java -jar Printer.jar" followed by arguments.

Arguments use the following syntax in the following order
print_size matte print_quantity rush discount

print_size is a string describing the size of the print.
Accepted values for print_size are '4x6', '5x7', '8x10'.

matte is a boolean describing if the order is matte or not.
Accepted values for matte are 'true' and 'false'.

print_quantity is an integer describing the number of prints of this type.
Accepted values for print_quantity is 1 through 100.

rush is a boolean describing if the order is 1-hour delivery.
Accepted values for rush are 'true' and 'false'.
For non-homogeneous orders, if any item is a rush order, the whole order is a rush order.

discount is an optional argument describing any possible discount code.

The program can accept non-homogeneous orders by appending new arguments afer the first set of arguments,
but before the discount code. For example, the following are valid sets of arguments.
print_size matte print_quantity rush
print_size matte print_quantity rush discount
print_size matte print_quantity rush print_size matte print_quantity rush
print_size matte print_quantity rush print_size matte print_quantity rush discount
print_size matte print_quantity rush print_size matte print_quantity rush print_size matte print_quantity rush
print_size matte print_quantity rush print_size matte print_quantity rush print_size matte print_quantity rush discount
ect.