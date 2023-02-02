import { defineConfig, loadEnv } from 'vite';
import * as path from 'path';
export default (mode) =>
  defineConfig({
    define: {
      'process.env': loadEnv(mode, process.cwd(), ''),
    },
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src'),
      },
    },
  });
