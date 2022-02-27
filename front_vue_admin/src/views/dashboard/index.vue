<template >
  <div id="app" style="text-align: center;">
    <img src="../../assets/bg_img/shi.jpg" style="width: 75%; height: 75%; margin-top:45px; border: 1px solid rgb(250, 218, 71);border-radius: 10px;">
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  }
}
</script>
  