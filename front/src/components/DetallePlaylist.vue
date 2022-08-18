<template>
    <div>
        <h1>Detalle playlist</h1>
        
        <h4 v-if="lists!= null">Nombre: {{lists.data.nombre}}</h4>      
        <h4 v-if="lists!= null">Descripcion: {{lists.data.descripcion}}</h4>
        <h3>Canciones:</h3>
        <ul v-if="lists!= null" v-for="item in lists.data.canciones">
             <li >Titulo: {{ item.titulo }}</li>
             <li >Artista: {{ item.artista }}</li>
              <li >Album: {{ item.album }}</li>
               <li >Año: {{ item.anno }}</li>
        </ul>
       
    </div>
</template>

<script setup lang="ts">
import {  onMounted, ref } from 'vue'
import Axios from 'axios';
import { useRoute } from 'vue-router'

const lists: any = ref(null);
const User = {
  template: '<div>User {{ $route.params.id }}</div>'
}       
 onMounted( async () => {
  const route = useRoute();
  const name =  route.params.name;
  lists.value = await Axios
      .get(`http://localhost:8080/playlist/lists/${name}`)

      console.log(lists.value)
             
})
</script>

<style scoped>

</style>