<script setup lang="ts">
import {  onMounted, ref } from 'vue'
import { useRouter } from 'vue-router';
import Axios from 'axios';



const router = useRouter();

const aux = ref(false)

const lists: any = ref(null);

onMounted( async () => {
  lists.value = await Axios
      .get('http://localhost:8080/playlist/lists')
             
})


function detail(name : string){
    router.push({path:`/detail/${name}`});
}
  
function deleteteList(name : string){ 
   Axios.delete(`http://localhost:8080/playlist/lists/${name}`)
     .then(response => {
     });
  
}



</script>

<template>
  <h1>Listas de reproducción</h1>

<div>
  <table class="table bordered striped">
    <thead>
      <tr>
        <th>Nombre</th>
          <th>Descripción</th>
           <th>Canciones</th>
           <th>Acción</th>
      </tr>
    </thead>
    <tbody>
      <tr  v-if="lists!= null" v-for="list in lists.data" >
        <td>{{list.nombre}}</td>
        <td>{{list.descripcion}}</td>
        <td><li v-for="item in list.canciones">
              {{ item.titulo }}
             </li></td>
         <td><button type="button"  @click="detail(list.nombre)">Ver</button>
         <button type="button"  @click="deleteteList(list.nombre)">Eliminar</button></td>
      </tr>
    </tbody>
  </table>
   
  </div>

  <br><br>
</template>

<style scoped>
a {
  color: #42b983;
}

label {
  margin: 0 0.5em;
  font-weight: bold;
}

code {
  background-color: #eee;
  padding: 2px 4px;
  border-radius: 4px;
  color: #304455;
}
</style>
