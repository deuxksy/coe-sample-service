

<template>

<div id="hello">
</div>

</template>

<script>

var Socket = require('simple-websocket')

export default {
    name: 'hello',

    created: function() {
      this.socket = new Socket('ws://localhost:7072/gs-guide-websocket');
      console.log(this.socket)
      this.socket.on('connect', function () {

          // socket is connected!
          // this.socket.send('{"chatRoomId": "'+this.$route.params.id+'","writer": "test","message": "test message","type": "JOIN"}')
          }.bind(this)
      )

      // this.socket.on('data', function (data) {
      //     console.log('got message: ' + data)
      // })
    }
}

</script>
