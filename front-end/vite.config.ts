import { defineConfig, loadEnv } from 'vite';
import * as path from 'path';
export default (mode) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) };
  return defineConfig({
    define: {
      'process.env': loadEnv(mode, process.cwd(), ''),
    },
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src'),
      },
    },
    server: {
      proxy: {
        '/api': {
          target: process.env.VITE_PUBLIC_API_BASEURL,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, ''),
          secure: false,
          ws: true,
        },
      },
    },
  });
};
