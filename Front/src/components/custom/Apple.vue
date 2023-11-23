<script setup>
import { ref, onMounted, reactive } from 'vue';
import * as THREE from 'three';
import { GLTFLoader } from "three/examples/jsm/loaders/GLTFLoader";

const isMounted = reactive({ value: false });
const container = ref();

const scene = new THREE.Scene();
scene.background = new THREE.Color('white');

const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.z = 5;

const renderer = new THREE.WebGLRenderer();
renderer.setSize(500, 500);
renderer.antialias = true;
renderer.outputColorSpace = THREE.SRGBColorSpace;

const loader = new GLTFLoader();
let shiba;

loader.load('/models/shiba/scene.gltf',
  (gltf) => {
    shiba = gltf.scene;
    // console.log(shiba);

    // shiba.traverse((child) => {
    //   if (child instanceof THREE.Mesh) {
    //     console.log("shiba : \n");
    //     console.log(child.name, child);
    //   }
    // })

    if (isMounted.value) {
      scene.add(shiba);
      animate();
    }
  }
);

function animate() {
  requestAnimationFrame(animate)
  shiba.rotation.y += 0.01
  renderer.render(scene, camera);
}

onMounted(() => {
  isMounted.value = true;
  container.value.appendChild(renderer.domElement);
});
</script>

<template>
  <div ref="container"></div>
</template>

<style scoped>

</style>