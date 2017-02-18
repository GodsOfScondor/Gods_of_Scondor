#version 400 core

in vec2 tex_coords;

out vec4 out_color;

uniform sampler2D img;
uniform float transparency;

void main(void) {

	if (transparency < 0.01) discard;
	
	vec4 color = texture(img,tex_coords);
	out_color = vec4(color.xyz, min(transparency, color.a));
}