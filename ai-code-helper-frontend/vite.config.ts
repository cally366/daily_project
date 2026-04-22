import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      // Backend base prefix: http://localhost:8082/api
      '/api': {
        target: 'http://localhost:8082',
        changeOrigin: true,
      },
    },
  },
})

