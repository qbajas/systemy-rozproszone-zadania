PORT = 8080
HOST = '127.0.0.1'

require 'socket'
include Socket::Constants

def create_subscription
  puts "Trying to create subscription"
  to_send = "POST /subscribe/localhost/cpu-usage HTTP/1.1"
  puts "Sent:"
  puts "  #{to_send}"
  socket = Socket.new(AF_INET, SOCK_STREAM, 0)
  sockaddr = Socket.pack_sockaddr_in(PORT, HOST)
  socket.connect(sockaddr)
  socket.write("#{to_send}\r\n\r\n")
  results = socket.read
  puts "Received:"
  puts "  "+results
  return results.split(" ")[2]
end

def get_connection_details(subscription_address)
  puts "Trying to get connection details"
  to_send = "GET "+subscription_address+ " HTTP/1.1"
  puts "Sent:"
  puts "  #{to_send}"
  socket = Socket.new(AF_INET, SOCK_STREAM, 0)
  sockaddr = Socket.pack_sockaddr_in(PORT, HOST)
  socket.connect(sockaddr)
  socket.write("#{to_send}\r\n\r\n")
  results = socket.read
  puts "Received:"
  puts "  "+results
  return results.split(" ")[2]
end

def receive_subscription_data(connection_details)
	puts "Connecting to socket"
	socket = Socket.new(AF_INET, SOCK_STREAM, 0)
	sockaddr = Socket.pack_sockaddr_in(connection_details.split(":")[1], connection_details.split(":")[0])
	socket.connect(sockaddr)
	counter=0
	puts "Receiving data "
	while(counter<3)
		puts "Received:\n	"+socket.readpartial(4096)
		counter+=1
	end
end

def cancel_subscription(subscription_address)
  puts "Deleting subscription"
  to_send = "DELETE "+subscription_address+ " HTTP/1.1"
  puts "Sent:"
  puts "  #{to_send}"
  socket = Socket.new(AF_INET, SOCK_STREAM, 0)
  sockaddr = Socket.pack_sockaddr_in(PORT, HOST)
  socket.connect(sockaddr)
  socket.write("#{to_send}\r\n\r\n")	
end


subscription_address = create_subscription
connection_details = get_connection_details(subscription_address)
receive_subscription_data(connection_details)
cancel_subscription(subscription_address)
