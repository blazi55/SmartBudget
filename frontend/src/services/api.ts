const API_URL = "http://localhost:8080/api";

export const api = {
  get: async (url: string) => {
    const res = await fetch(`${API_URL}${url}`);

    if (!res.ok) {
      const text = await res.text();

      console.error("API ERROR:", {
        url,
        status: res.status,
        body: text,
      });

      throw new Error(`GET ${url} failed (${res.status})`);
    }

    return res.json();
  },

  post: async (url: string, body: any) => {
    const res = await fetch(`${API_URL}${url}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });

    if (!res.ok) throw new Error("POST error");
    return res.json();
  },

  delete: async (url: string) => {
    const res = await fetch(`${API_URL}${url}`, {
      method: "DELETE",
    });

    if (!res.ok) throw new Error("DELETE error");
  },
};