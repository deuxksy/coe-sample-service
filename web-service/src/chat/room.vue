<template>
  <div id="room">
      {{this.$route.params.id}}
  </div>
</template>
<script>
var Socket = require('simple-websocket')

export default {
  name: 'room',
  data () {
        return {
            socket: {}
        }
  },
  mounted: function(){
      if(this.$route.params.id) {
        this.socket = new Socket('ws://localhost:7997/api/v1/chat/ws');
        console.log(this.socket)
        this.socket.on('connect', function () {
             
            // socket is connected!
            this.socket.send('{"chatRoomId": "'+this.$route.params.id+'","writer": "test","message": "test message","type": "JOIN"}')
            }.bind(this)
        )

        this.socket.on('data', function (data) {
            console.log('got message: ' + data)
        })
      }
  }
}
</script>