import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useCounterStore = defineStore("counter", () => {
  const count = ref(0);
  const name = ref("");
  const doubleCount = computed(() => count.value * 2);
  function increment() {
    count.value++;
  }
  const addList = ref([]);
  const name1 = ref("");

  return { name, count, doubleCount, increment, name1, addList };
});
