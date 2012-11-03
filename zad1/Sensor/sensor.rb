PORT = 8081
HOST = '127.0.0.1'

require 'socket'
include Socket::Constants

def send_measurements
	send_measurement('cpu-usage')
	send_measurement('memory-usage')
	send_measurement('no-processes')
end

def send_measurement(name)
  puts "Sending measurements "
  to_send = "POST /measurement/localhost/#{name}/#{get_value(name)}/#{Time.now.strftime('%s')} HTTP/1.1"
  puts "  #{to_send}"
  socket = Socket.new(AF_INET, SOCK_STREAM, 0)
  sockaddr = Socket.pack_sockaddr_in(PORT, HOST)
  socket.connect(sockaddr)
  socket.write("#{to_send}\r\n\r\n")
  results = socket.read
  puts "Received"
  puts "  "+results
end

def get_value(metric)
	case metric
	when 'cpu-usage'
		rand().round(2)
	when 'memory-usage'
		(rand()*1000).round+10
	when 'no-processes'
		(rand()*100).round+1
	end  
end

send_measurements
while(true)
  puts "Press enter to make measurements again and send it to monitor "
  gets
  send_measurements
end

